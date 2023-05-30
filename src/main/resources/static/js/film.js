"use strict";
import {byId, toon, setText, verbergClass} from "./util.js";
const film = sessionStorage.getItem(`film${sessionStorage.length - 1}`);
const filmJson = JSON.parse(film);
const filmId = filmJson.id;
const beschikbaar = filmJson.voorraad - filmJson.gereserveerd;

const inMandje = byId("inMandje");
verbergClass("fout");

        // Film info

setText("titel", filmJson.titel);
byId("foto").src = `images/${filmId}.jpg`;
setText("prijs", filmJson.prijs);
setText("voorraad", filmJson.voorraad);
setText("gereserveerd", filmJson.gereserveerd);
setText("beschikbaar", beschikbaar);

        // Button In Mandje

inMandje.disabled = beschikbaar === 0;

inMandje.onclick = function () {
    window.location = "mandje.html";
}

// Als op de Startpagina geclickt wordt, wordt de film van Session Storage weggehaald
byId("startpagina").onclick = function () {
    sessionStorage.removeItem(`film${sessionStorage.length - 1}`);
}

if (film.length === 0) {
    toon("storing");
}


