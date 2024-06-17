// src/main/resources/static/js/app.js
import { populateCarModels, toggleNewCarForm } from './carModels.js';
import { populateServiceCenters } from './serviceCenters.js';
import { makeAppointment, toggleAllAppointments, toggleAllCars } from './appointments.js';
import { initMap } from './googleMaps.js';

document.addEventListener('DOMContentLoaded', function () {
    populateCarModels();
    populateServiceCenters();

    const appointmentForm = document.getElementById('appointmentForm');
    appointmentForm.addEventListener('submit', makeAppointment);
});

window.toggleNewCarForm = toggleNewCarForm;
window.toggleAllAppointments = toggleAllAppointments;
window.toggleAllCars = toggleAllCars;
window.initMap = initMap;
