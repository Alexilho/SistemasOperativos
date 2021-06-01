package modelo;

import java.util.Random;

public class Nombres {
    
    private String [] nombre=
    {
        "Abe",
        "Homero",
        "Ramon",
        "Marcos",
        "Ezequiel",
        "Chavo",
        "Flor",
        "Danna",
        "Estefania",
        "Romeo",
        "Juana",
        "Jesus",
        "David",
        "Gabriel",
        "Lola",
        "Lalo",
        "Otto",
        "Luis",
        "Tota",
        "Rafael",
        "Daniela",
        "Andrea",
        "Goku",
        "Picoro",
        "Juan",
        "Miguel",
        "Sebastian",
        "Cristian",
        "Jeferson",
        "Tito",
        "Rita",
        "Rosa",
        "Gabriel",
        "Sofia",
        "Sara",
        "Fernando",
        "Alejandro",
        "Wilson",
        "Jessica",
        "Natalie",
        "Rito",
        "Jeremias",
        "Jonas",
        "Damian",
        "Gustavo",
        "Aatrox",
        "Jinx",
        "Milay",
        "Bruno",
        "Reynel",
        "Cleopatra",
        "Ezio",
        "Laura",
        "Marie",
        "Albert",
        "Martin",
        "Clara",
        "Emma",
        "Scarlett",
        "Ivveth",
        "Yeimmi",
        "Paula",
        "Shiddarta",
        "Kitabatake",
        "Minanoto",
        "Nobunaga",
        "Naruto",
        "Daissi",
        "Cristian",
        "Carlos",
        "Brayan"
    };
    
    private int Cantidad(){
        return this.nombre.length;
    }
    
    public String generarnombre(){
        Random ran= new Random();
        int num=ran.nextInt(Cantidad());
        return this.nombre[num];
    }
}