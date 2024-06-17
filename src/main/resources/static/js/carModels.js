export function populateCarModels() {
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

export function toggleNewCarForm() {
    const carModelSelect = document.getElementById('carModel');
    const newCarForm = document.getElementById('newCarForm');
    newCarForm.classList.toggle('hidden', carModelSelect.value !== "new");
}
