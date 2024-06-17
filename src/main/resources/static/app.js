document.addEventListener('DOMContentLoaded', function () {
    populateCarModels();

    const appointmentForm = document.getElementById('appointmentForm');
    appointmentForm.addEventListener('submit', makeAppointment);
});

function populateCarModels() {
    fetch('/cars')
        .then(response => response.json())
        .then(data => {
            const carModelSelect = document.getElementById('carModel');
            carModelSelect.innerHTML = '<option value="">Select Car Model</option>';
            data.forEach(car => {
                const option = document.createElement('option');
                option.value = car.id;
                option.textContent = `${car.make} ${car.model} (${car.year})`;
                carModelSelect.appendChild(option);
            });
            const option = document.createElement('option');
            option.value = "new";
            option.textContent = "My car is not listed";
            carModelSelect.appendChild(option);
        })
        .catch(error => console.error('Error:', error));
}

function toggleNewCarForm() {
    const carModelSelect = document.getElementById('carModel');
    const newCarForm = document.getElementById('newCarForm');
    newCarForm.classList.toggle('hidden', carModelSelect.value !== "new");
}

function makeAppointment(event) {
    event.preventDefault();

    const carId = document.getElementById('carModel').value;
    const appointmentDateTime = document.getElementById('appointmentDateTime').value;
    const serviceDescription = document.getElementById('serviceDescription').value;

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
                    serviceDescription: serviceDescription
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
            serviceDescription: serviceDescription
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

function toggleAllCars() {
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

function toggleAllAppointments() {
    const appointmentTable = document.getElementById('appointmentTable');
    if (appointmentTable.style.display === 'none') {
        fetch('/appointments')
            .then(response => response.json())
            .then(data => {
                return Promise.all(data.map(appointment => {
                    return fetch(`/cars/${appointment.carId}`)
                        .then(response => response.json())
                        .then(carData => {
                            appointment.carMakeModel = `${carData.make} ${carData.model}`;
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

function initMap() {
    const vilnius = { lat: 54.6872, lng: 25.2797 };
    const map = new google.maps.Map(document.getElementById("map"), {
        zoom: 13,
        center: vilnius,
    });

    const service = new google.maps.places.PlacesService(map);
    service.nearbySearch({
        location: vilnius,
        radius: 5000,
        type: ['car_repair']
    }, function(results, status) {
        if (status === google.maps.places.PlacesServiceStatus.OK) {
            for (let i = 0; i < results.length; i++) {
                createMarker(results[i]);
            }
        } else {
            alert('Places service was not successful for the following reason: ' + status);
        }
    });

    function createMarker(place) {
        new google.maps.Marker({
            map,
            position: place.geometry.location,
            title: place.name
        });
    }
}
