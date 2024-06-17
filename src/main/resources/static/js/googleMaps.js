// src/main/resources/static/js/googleMaps.js
export function initMap() {
    const vilnius = { lat: 54.722470, lng: 25.289500 };
    const map = new google.maps.Map(document.getElementById("map"), {
        zoom: 13,
        center: vilnius,
    });

    // Predefined locations
    const locations = [
        { lat: 54.722470, lng: 25.289500, name: 'Tire change' },
        { lat: 54.722037, lng: 25.295260, name: 'Autoservice' },
        { lat: 54.722470, lng: 25.281500, name: 'Stop-Servis' },
        // { lat: 54.6840, lng: 25.2829, name: 'Car Fix' },
        // { lat: 54.6951, lng: 25.2753, name: 'Speedy Repair' }
    ];

    // Create markers for predefined locations
    locations.forEach(location => {
        createMarker(location);
    });

    // Nearby search for car repair places
    const service = new google.maps.places.PlacesService(map);
    service.nearbySearch({
        location: vilnius,
        radius: 5000,
        type: ['car_repair']
    }, function(results, status) {
        if (status === google.maps.places.PlacesServiceStatus.OK) {
            results.forEach(place => {
                createMarker({
                    lat: place.geometry.location.lat(),
                    lng: place.geometry.location.lng(),
                    name: place.name
                });
            });
        } else {
            alert('Places service was not successful for the following reason: ' + status);
        }
    });

    // Function to create a marker
    function createMarker(location) {
        const marker = new google.maps.Marker({
            map,
            position: { lat: location.lat, lng: location.lng },
            title: location.name
        });

        // Info window content
        const infoWindowContent = `<h3>${location.name}</h3><p>Lat: ${location.lat}, Lng: ${location.lng}</p>`;

        // Create info window
        const infoWindow = new google.maps.InfoWindow({
            content: infoWindowContent
        });

        // Add click listener to marker
        marker.addListener('click', () => {
            infoWindow.open(map, marker);
        });
    }
}
