"use strict";
import {byId, toon, setText, verberg, verwijderChildElementenVan} from "./util.js";

const filmSetBody = byId("filmSet");
const tdTotaal = byId("total");
    verwijderChildElementenVan(filmSetBody);
    mandjeInvullen (filmSetBody)
    updateTotal(tdTotaal, filmSetBody);

    function updateTotal (tdTotaal, tbody) {
        let totaal = 0;
        for (const tr of tbody.rows) {
            totaal += Number(tr.cells[1].innerText);
        }
        tdTotaal.innerText = totaal;
    }

    function mandjeInvullen (tbody) {
        for (let i = 0; i < sessionStorage.length; i++) {
            const item = JSON.parse(sessionStorage.getItem(`film${i}`));
            const tr = tbody.insertRow();
            tr.insertCell().innerText = item.titel;
            tr.insertCell().innerText = item.prijs;
        }
    }