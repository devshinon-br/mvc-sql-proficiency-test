const saveVehicle = (event) => {
    event.preventDefault();

    const form = $('#form-vehicle')[0];

    const formData = new FormData(form);

    fetch(form.action, {
        method: form.method,
        body: formData
    })
    .then(response => {
        response.json()
        .then(json => {
            if(json) {
                if(json.entryTime && json.licensePlate) {
                    $('#modal-time').text(json.entryTime);
                    $('#modal-license-plate').text(json.licensePlate);
                    $('#modal-ticket').modal('show');
                    return;
                }

                if(json.errorMessage) {
                    $('#msg-error').text(json.errorMessage);
                    $('#modal-error').modal('show');
                }
            }
        })
    })
    .catch(error => {
        console.error(error);
    });
}
