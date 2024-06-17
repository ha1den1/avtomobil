// src/main/resources/static/js/googleMaps.js
export function initMap() {
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
            results.forEach(place => {
                createMarker(place);
            });
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
