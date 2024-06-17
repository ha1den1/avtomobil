// src/main/resources/static/js/serviceCenters.js
export function populateServiceCenters() {
    fetch('/service-centers')
        .then(response => response.json())
        .then(data => {
            const serviceCenterSelect = document.getElementById('mechanicCenter');
            serviceCenterSelect.innerHTML = '<option value="">Select Mechanic Center</option>';
            data.forEach(center => {
                const option = document.createElement('option');
                option.value = center.id;
                option.textContent = `${center.name} - ${center.address}`;
                serviceCenterSelect.appendChild(option);
            });
        })
        .catch(error => console.error('Error:', error));
}
