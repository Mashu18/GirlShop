var contadorDia = new Date('Dec 31, 2022 00:00:00').getTime();

function nuevoAño(){
    var ahora = new Date().getTime();
    gap = contadorDia - ahora;

    var second = 1000;
    var minute = second * 60;
    var hour = minute * 60;
    var day = hour * 24;

    var d = Math.floor(gap/(day));
    var h = Math.floor((gap % (day)/(hour)));
    var m = Math.floor((gap % (hour)/(minute)));
    var s = Math.floor((gap % (minute)/(second)));

    document.getElementById('Day').innerText = d;
    document.getElementById('Hour').innerText = h;
    document.getElementById('Minute').innerText = m;
    document.getElementById('Second').innerText = s;
}

setInterval(function(){
    nuevoAño();
},1000)