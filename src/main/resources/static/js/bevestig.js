"use strict";
import {byId, toon, setText, verberg} from "./util.js";
const klant = JSON.parse(sessionStorage.getItem("klant"));
const bevestig = byId("bevestig");
const filmLijst = byId("filmsLijst");

verberg("storing");

setText("aantal", sessionStorage.length - 1);
setText("naam", `${klant.familienaam} ${klant.voornaam}`);

const reservatie = {
    klantId: klant.id
}
toon("mandje");
bevestig.disabled = false;
    bevestig.onclick = async function () {

        // Alle gekozen en bewaard in Session Storage films overlopen en reserveren

        for (let i = 0; i < sessionStorage.length; i++) {
            const film = JSON.parse(sessionStorage.getItem(`film${i}`));
            const filmId = film.id;
            filmsReserveren (filmId, film, filmLijst);
        }

        toon("filmsLijst");
        verberg("mandje");
        bevestig.disabled = true;
        sessionStorage.clear();
    }

     // KLANT object van Session Storage verwijderen als op Startpagina of Mandje geclickt wordt
document.querySelectorAll("a").forEach(
    occurence => sessionStorage.removeItem("klant")
)

async function filmsReserveren (filmId, film, filmLijst) {
    const reservatieLukte = "OK";
    const reservatieMislukte = "Uitverkocht";
    const filmNietGevonden = "niet gevonden";

    const response = await fetch(`reservaties/${filmId}`,
        {
            method: "PATCH",
            headers: {'Content-Type':"application/json"},
            body: JSON.stringify(reservatie)
        });

    const li = document.createElement("li");

    if (response.ok) {
        li.innerText = `${film.titel}: ${reservatieLukte}`;

    } else {
        switch (response.status) {
            case 409:
                li.innerText = `${film.titel}: ${reservatieMislukte}`;
                break;
            case 404:
                li.innerText = `${film.titel}: ${filmNietGevonden}`;
                break;
            default:
                toon("storing");
        }
    }
    filmLijst.appendChild(li);
}