export function initMap() {
    const vilnius = { lat: 54.722470, lng: 25.289500 };
    const map = new google.maps.Map(document.getElementById("map"), {
        zoom: 13,
        center: vilnius,
    });
    const locations = [
        { lat: 54.722470, lng: 25.289500, name: 'Tire change' },
        { lat: 54.722037, lng: 25.295260, name: 'Autoservice' },
        { lat: 54.722470, lng: 25.281500, name: 'Stop-Servis' },

    ];
    locations.forEach(location => {
        createMarker(location);
    });
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

    function createMarker(location) {
        const marker = new google.maps.Marker({
            map,
            position: { lat: location.lat, lng: location.lng },
            title: location.name
        });

        const infoWindowContent = `<h3>${location.name}</h3><p>Lat: ${location.lat}, Lng: ${location.lng}</p>`;

        const infoWindow = new google.maps.InfoWindow({
            content: infoWindowContent
        });

        marker.addListener('click', () => {
            infoWindow.open(map, marker);
        });
    }
}
