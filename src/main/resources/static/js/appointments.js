import { populateCarModels, toggleNewCarForm } from './carModels.js';
import { populateServiceCenters } from './serviceCenters.js';

export function makeAppointment(event) {
    event.preventDefault();

    const carId = document.getElementById('carModel').value;
    const appointmentDateTime = document.getElementById('appointmentDateTime').value;
    const serviceDescription = document.getElementById('serviceDescription').value;
    const mechanicCenterId = document.getElementById('mechanicCenter').value;

    let appointment;

    if (carId === "new") {
        const newCarMake = document.getElementById('newCarMake').value;
        const newCarModel = document.getElementById('newCarModel').value;
        const newCarYear = document.getElementById('newCarYear').value;

        const newCar = {
            make: newCarMake,
            model: newCarModel,
            year: newCarYear
        };

        fetch('/cars', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newCar)
        })
            .then(response => response.json())
            .then(carData => {
                const carModelSelect = document.getElementById('carModel');
                const option = document.createElement('option');
                option.value = carData.id;
                option.textContent = `${carData.make} ${carData.model} (${carData.year})`;
                carModelSelect.appendChild(option);
                carModelSelect.value = carData.id;

                appointment = {
                    carId: carData.id,
                    appointmentDateTime: appointmentDateTime,
                    serviceDescription: serviceDescription,
                    serviceCenter: { id: mechanicCenterId }
                };

                return fetch('/appointments', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(appointment)
                });
            })
            .then(response => response.json())
            .then(data => {
                document.getElementById('message').innerHTML = `<p>Appointment created successfully with ID: ${data.id}</p>`;
            })
            .catch(error => console.error('Error:', error));
    } else {
        appointment = {
            carId: carId,
            appointmentDateTime: appointmentDateTime,
            serviceDescription: serviceDescription,
            serviceCenter: { id: mechanicCenterId }
        };

        fetch('/appointments', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(appointment)
        })
            .then(response => response.json())
            .then(data => {
                document.getElementById('message').innerHTML = `<p>Appointment created successfully with ID: ${data.id}</p>`;
            })
            .catch(error => console.error('Error:', error));
    }
}

export function toggleAllAppointments() {
    const appointmentTable = document.getElementById('appointmentTable');
    if (appointmentTable.style.display === 'none') {
        fetch('/appointments')
            .then(response => response.json())
            .then(data => {
                return Promise.all(data.map(appointment => {
                    return Promise.all([
                        fetch(`/cars/${appointment.carId}`).then(response => response.json()),
                        fetch(`/service-centers/${appointment.serviceCenter.id}`).then(response => response.json())
                    ]).then(([carData, serviceCenterData]) => {
                        appointment.carMakeModel = `${carData.make} ${carData.model}`;
                        appointment.serviceCenterName = serviceCenterData.name;
                        return appointment;
                    });
                }));
            })
            .then(appointments => {
                const appointmentTableBody = document.getElementById('appointmentTableBody');
                appointmentTableBody.innerHTML = '';
                appointments.forEach(appointment => {
                    const row = `<tr>
                                    <td>${appointment.id}</td>
                                    <td>${appointment.carMakeModel}</td>
                                    <td>${appointment.appointmentDateTime}</td>
                                    <td>${appointment.serviceDescription}</td>
                                    <td>${appointment.serviceCenterName}</td>
                                </tr>`;
                    appointmentTableBody.innerHTML += row;
                });
                appointmentTable.style.display = 'block';
            })
            .catch(error => console.error('Error:', error));
    } else {
        appointmentTable.style.display = 'none';
    }
}

export function toggleAllCars() {
    const carTable = document.getElementById('carTable');
    if (carTable.style.display === 'none') {
        fetch('/cars')
            .then(response => response.json())
            .then(data => {
                const carTableBody = document.getElementById('carTableBody');
                carTableBody.innerHTML = '';
                data.forEach(car => {
                    const row = `<tr>
                                    <td>${car.id}</td>
                                    <td>${car.make}</td>
                                    <td>${car.model}</td>
                                    <td>${car.year}</td>
                                </tr>`;
                    carTableBody.innerHTML += row;
                });
                carTable.style.display = 'block';
            })
            .catch(error => console.error('Error:', error));
    } else {
        carTable.style.display = 'none';
    }
}
