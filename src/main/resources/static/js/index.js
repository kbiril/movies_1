"use strict";
import {byId, toon, setText, verberg, verwijderChildElementenVan, verbergClass} from "./util.js";
findAllGenres();
async function findAllGenres() {
    verbergClass("fout");
    const response = await fetch("genres");
    if (response.ok) {
        const genres = await response.json();
        const genresUl = byId("genres");

        for (const genre of genres) {
            const genreId = genre.id;
            const genreNaam = `${genre.naam}`;

            const a = createLiA(genresUl)
            a.innerText = genreNaam;

            a.onclick = async function (){
                const filmsUl = byId("films");
                verwijderChildElementenVan(filmsUl);
                const response = await fetch(`genres/${genreId}`);
                if (response.ok) {
                    const films = await response.json();

                    toon("filmsPerGenre");
                    setText("genreNaam", genreNaam);

                    if (films.length !== 0) {
                        verbergClass("fout");
                        for (const film of films) {
                            const a = createLiA(filmsUl);
                            const img = document.createElement("img");

                            a.appendChild(img);
                            a.href = "#";
                            img.src = `images/${film.id}.jpg`;

                            a.onclick = async function(){
                                window.location = "film.html";
                                safeMovieIfUnique(film);
                            }
                        }
                    } else {
                        toon("filmsFout");
                    }
                } else {
                    toon("storing");
                }
            }
        }
    }
    else {
        toon("storing");
    }
}

function safeMovieIfUnique (film) {
    let uniekId = true;
    for (let i = 0; i < sessionStorage.length; i++) {
        if (JSON.parse(sessionStorage.getItem(`film${i}`)).id === film.id) {
            uniekId = false;
            return;
        }
    }
    if (uniekId){
        sessionStorage.setItem(`film${sessionStorage.length}`, JSON.stringify(film));
    }
}

function createLiA(ul) {
    const li = document.createElement("li");
    const a = document.createElement("a");
    li.appendChild(a);
    ul.appendChild(li);
    return a;
}

