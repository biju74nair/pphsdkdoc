var request = require('request');

// Set the headers
var headers = {
    'User-Agent':       'PPH Docs Agent/0.0.1',
    'Content-Type':     'application/x-www-form-urlencoded',
    'Authorization':    'Basic QVN1eEEtWV9BNGloY21wNWNraTh4YlFBUnBvcUlGbUVEV1BnWmt5cWZqOUJyQXcxd0wyTFpaUTl1cnhFdVprbU0zQU1lY3ZJLXZleTdUVUE6RUdTdVl2ZFpfcUIwQ1UxTXZqRk0ybk5nbjJMaXRfMzhaQnE5ZUFGTS1wb1pycnlaT0dVU2haOWFSMHZ3WjdSTDM0N0w0VUZVNkQteVpuazA='
}

// Configure the request
var options = {
    url: 'https://api.sandbox.paypal.com/v1/identity/openidconnect/tokenservice',
    method: 'POST',
    headers: headers,
    form: {'grant_type': 'refresh_token', 'refresh_token': 'R103.J66QjQhVZc5u86zUMJ0GMPMPNByxh1RN3HuoeyY-meIWIzI1UbhiP8eqUkwG4ZAOaFsxAoKo6jRQrD2LdQIocit3DyKB1APy6IPzHwS5Al_dcH8UlcWB2D97gi4AvXyntLC4KsGLWBX763an'}
}


exports.accesstoken =function(req, resp) {
    request(options, function (error, response, body) {
        if (!error && response.statusCode == 200) {
            resp.send(response.body);
        } else {
            resp.status(500);
        }
    })

    //resp.send({"token_type":"Bearer","expires_in":"28800","access_token":"A23AAF-5ERhsD6Gzr4IaRkd2SsmnCFmIbCBvUwnl981IL-HYdeOZPKM4TO8F-9j30IGh0igs0joJIYNk806xJRaOgHkRvy5fg"});
}    

