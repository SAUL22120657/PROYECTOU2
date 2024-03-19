package com.singleton.peliculascrud;

public class Pelicula {

        private String nombre;
        private String isbn;
        private String stock;
        private String ano;
        private String compania;

        public Pelicula(String nombre, String isbn, String stock, String ano, String compania) {
            this.nombre = nombre;
            this.isbn = isbn;
            this.stock = stock;
            this.ano = ano;
            this.compania = compania;
        }

        // Métodos getter y setter para cada atributo
        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getIsbn() {
            return isbn;
        }

        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        public String getAno() {
            return ano;
        }

        public void setAno(String ano) {
            this.ano = ano;
        }

        public String getCompania() {
            return compania;
        }


        public void setCompania(String compania) {
            this.compania = compania;
        }
    }

