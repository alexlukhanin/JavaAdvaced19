let studentResponse;
function ready() {

    let xhr = new XMLHttpRequest();
    xhr.responseType = 'json';
    xhr.open("GET", "downloadFileThis");
    xhr.onload = function() {

        studentResponse = xhr.response;

        let context =
            '<div class="img">' +
            '<img src="' + studentResponse.fileDownloadUri + '" alt="">'+
            '</div>' +
            '<div class=student>' +
            '<p>First name :  ' + studentResponse.firstName + '</p>'+
            '<p>Last name : '+ studentResponse.lastName + '</p>' +
            '<p>Age : ' + studentResponse.age + '</p>';

        document.getElementById('paste').innerHTML = context;

    }
    xhr.send();


}

document.addEventListener("DOMContentLoaded", ready);