"use strict";
import {byId, toon, verberg, verwijderChildElementenVan, verbergClass} from "./util.js";

byId("zoek").onclick = async function() {
    verbergClass("fout");

    const woordInput = byId("woord");
    if (woordInput.checkValidity()) {
        verberg("woordFout");
        findKlantenWoord(woordInput.value);
    } else {
        toon("woordFout");
        woordInput.focus();
    }
}

async function findKlantenWoord(woord) {
    const response = await fetch(`klanten?woord=${woord}`);
    const responseBody = await response.json();

    if (response.ok) {
        const klantenBody = byId("klantenBody");
        verwijderChildElementenVan(klantenBody);

        if (responseBody.length !== 0) {
            toon("klantenTable");
            klantenInvullen(klantenBody, responseBody);
        } else {
            verberg("klantenTable");
            toon("geenKlant");
        }
    }
}
function klantenInvullen (tbody, responseBody) {
    for (const klant of responseBody) {
        const tr = tbody.insertRow();

        const a = document.createElement("a");
        a.innerText = `${klant.familienaam} ${klant.voornaam}`;
        a.href = "#";
        a.onclick = async function () {
            window.location = "bevestig.html"
            sessionStorage.setItem("klant", JSON.stringify(klant));
        }

        tr.insertCell().appendChild(a);
        tr.insertCell().innerText = klant.straatNummer;
        tr.insertCell().innerText = klant.postcode;
        tr.insertCell().innerText = klant.gemeente;
    }
}

