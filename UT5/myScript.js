$(document).ready(function() {
    // Que hacer cuando esté lista la página
    // [2a]
    $('#borrar').click(function(event) {
        $('#pie').html('');
    });
    // [2b]
    $('#txt').click(function(event) {
        let varExpresion = $('#expresion').val();
        $.get('https://luiscastelar.duckdns.org/holaMundo.txt', 
                { datos : varExpresion }, 
                function(responseText){
            $('#pie').html(responseText);
        });
    });
    // [2c]
    $('#json').click(function(event) {
        // Capturamos valor
        let varExpresion = $('#expresion').val();
        
        // Generamos json para transmitir datos [es opcional]. Podríamos pasarlo en el search de la URL
        let jsonExpresion = { expresion: varExpresion }
        
        // Generamos la URL [opcional]. Podríamos pasar directamente el string con la URL
        const PROTO = "https";
        const FQDN = "luiscastelar.duckdns.org";
        const PORT = ""; // [:8080]
        const PATHNAME = "/holaMundo.json";
        const SEARCH = ""; // [?exp=2+2++]
        const HASH = ""; // [#hash] -> Típicamente para ayudar a ajax a ubicar la peticion
        const url = new URL(PROTO + "://" + FQDN + PORT + PATHNAME + SEARCH + HASH);
        
        // Hacemos la llamada. Si no vamos a enviar datos podemos omitir [, jsonExpresion]
        $.getJSON(url, jsonExpresion, function( data ) {
        
            // Recorremos todos los datos obtenidos y los asignamos al array items
            let items = [];
            $.each( data, function( key, val ) {
            items.push( "<li id='" + key + "'>[" + key + "]=> " + val + "</li>" );
            });
            
            // Serializamos el array items y lo insertamos en nuestro bloque pie
            $("#pie").html("<ul>" + items.join("") + "</ul>");
            
        }); // $.getJson
    }); // $.json
}); // document ready