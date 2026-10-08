<?php
//para mostrar por pantalla 
echo("Hola");

$nombre= "Benjamin";
$apellido="Berrios";
$edad= 17;


echo( $nombre);
echo(gettype($nombre)." ".gettype($nombre));

echo( $apellido);
echo(gettype($apellido)." ".gettype($apellido));

echo( $edad);
echo(gettype($edad)." ".gettype($edad));

$numero1 = 15;
$numero2 = 10;
$resulSuma = $numero1 + $numero2;
$resulResta = $numero1 - $numero2;
$resulMulti = $numero1 * $numero2;
$resulDiv = $numero1 / $numero2;

echo "los numeros son".$numero1. "y".$numero2;
echo "el resultado de la suma es".$resulSuma;
echo "el resultado de la resta es".$resulResta;
echo "el resultado de la multiplicacion es".$resulMulti;
echo "el resultado de la division es".$resulDiv;

if($edad <= 18 ) {
    echo "Sos mayor de edad"
}else {
    echo "Sos menor de edad"
}

$nota = 8;

switch (true) {

case $nota >= 1 && $nota <= 3:
     echo "Desaprobado";
 break;
case $nota >= 4 && $nota <= 6:
     echo "Aprobado"; 
break;
case $nota >= 7 && $nota <= 8: 
    echo "Muy bien"; 
break;
case $nota >= 9 && $nota <= 10: 
    echo "Excelente"; 
break;
default: 
echo "Nota inválida"; }

$numero3 = 78; 
$numero4 = 43; 

if ($numero3 > $numero4) { 
    echo "El número mayor es " . $numero3; } 
elseif ($numero4 > $numero3) {
     echo "El número mayor es " . $numero4; } 
else { 
    echo "Los números son iguales"; }

if ($edad < 13) { 
    echo "Niño/a"; } 
elseif ($edad >= 13 && $edad <= 17) { 
    echo "Adolescente"; } 
elseif ($edad >= 18 && $edad <= 64) { 
    echo "Adulto/a"; } 
else { 
    echo "Adulto/a mayor"; }

  
?>