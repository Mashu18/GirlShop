var t = 0;
function reloj(){
    t++;
    if(t>7) t = 1;
    document.getElementById("Banner").setAttribute("src","../images/banners/banner"+t+".webp");
    setTimeout("reloj()",3000);
}
reloj();