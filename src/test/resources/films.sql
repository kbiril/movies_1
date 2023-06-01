insert into films (genreId, titel, voorraad, gereserveerd, prijs) values
                  ((select id from genres where naam = 'test1'), 'titelTest1', 1, 0, 5),
                  ((select id from genres where naam = 'test1'), 'titelTest2', 10, 10, 10);