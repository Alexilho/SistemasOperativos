#!/bin/bash


echo '%%%%%%%%%%%%%%%%%   necesita un parametro numerico   %%%%%%%%%%%'

read -p " ingresa celular: " cel

 # recomnendaxionea evaluacion de, dejar separado de [], elif then
 # puedes revisar
case $cel in

  30[0-5]*)
    echo 'eres de tigo'
  ;;
  31[0-4]*)
    echo 'eres de claro'
  ;;
  31[5-9]*)
    echo 'eres de claro'
  ;;
  320*)
    echo 'quizas seas de claro'
  ;;
  3*)
    echo  ' quizas seas  de otro operador'
  ;;
  *)
    echo 'Algo no esta bien, todos los operadores empiezan por 3'
  ;;
esac
