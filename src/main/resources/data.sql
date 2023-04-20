INSERT INTO CITALAC (id, admin, ime, prezime, korisnicko_ime, email, lozinka, datum_rodjenja, profilna_slika) VALUES (1, false, 'J. K.', 'Rowling', 'rolovana', 'jkrowling@harrypotter.com', 'rolnica', '1965-07-31', '/slike/1.jpg');
INSERT INTO CITALAC (id, admin, ime, prezime, korisnicko_ime, email, lozinka) VALUES (2, true, 'Dejan', 'Bogovac', 'Cutthroat', 'bogovac.in20.2021@uns.ac.rs', 'test123');
INSERT INTO CITALAC (id, admin, ime, prezime, korisnicko_ime, email, lozinka) VALUES (3, true, 'Anastasija', 'Terzic', 'Anastasija', 'terzic.in44.2021@uns.ac.rs', 'test123');

INSERT INTO POLICA (naziv, primarna, korisnik_id) VALUES ('Want To Read', true, 1);
INSERT INTO POLICA (naziv, primarna, korisnik_id) VALUES ('Currently Reading', true, 1);
INSERT INTO POLICA (naziv, primarna, korisnik_id) VALUES ('Read', true, 1);
INSERT INTO POLICA (naziv, primarna, korisnik_id) VALUES ('Test', false, 1);
INSERT INTO POLICA (naziv, primarna, korisnik_id) VALUES ('Want To Read', true, 2);
INSERT INTO POLICA (naziv, primarna, korisnik_id) VALUES ('Currently Reading', true, 2);
INSERT INTO POLICA (naziv, primarna, korisnik_id) VALUES ('Read', true, 2);
INSERT INTO POLICA (naziv, primarna, korisnik_id) VALUES ('Want To Read', true, 3);
INSERT INTO POLICA (naziv, primarna, korisnik_id) VALUES ('Currently Reading', true, 3);
INSERT INTO POLICA (naziv, primarna, korisnik_id) VALUES ('Read', true, 3);

INSERT INTO AUTOR (id, aktivan) VALUES (1, true);
INSERT INTO AUTOR (id, aktivan) VALUES (3, false);

INSERT INTO ZAHTEV (email, telefon, poruka, datum, status, autor_id) VALUES ('terzic.in44.2021@uns.ac.rs', '+111 111 111', 'pls gib author uwu >///<', '2023-04-10', 'CEKA', 3);

INSERT INTO KNJIGA (naslov,naslovna_fotografija,ISBN,datum_objavljivanja,broj_strana,opis,ocena) VALUES ('Sta nam svako telo govori','www.nesto.srbijaaaaa',12345,'2002-09-16',300,'bas dobra knjiga',10.0);
INSERT INTO KNJIGA (naslov,naslovna_fotografija,ISBN,datum_objavljivanja,broj_strana,opis,ocena) VALUES ('HARRY POTTER','www.hair_poter.kegenda',19945,'2000-01-11',500,'najjaca knjiga ikada!!!!',9.0);

INSERT INTO ZANR(naziv)VALUES ('akcija');
INSERT INTO ZANR(naziv)VALUES ('edukativna');
INSERT INTO ZANR(naziv)VALUES ('drama');
INSERT INTO ZANR(naziv)VALUES ('komedija');
INSERT INTO ZANR(naziv)VALUES ('romantika');
INSERT INTO ZANR(naziv)VALUES ('horor');
INSERT INTO ZANR(naziv)VALUES ('triler');

INSERT INTO RECENZIJA(ocena,tekst,datum_recenzije, korisnik_id)VALUES (10.0,'sve bajno, sve sjajno. ala je lep ovaj svet ovde potok onde cvet ','2021-04-04', 2);
INSERT INTO RECENZIJA(ocena,tekst,datum_recenzije, korisnik_id)VALUES (1.0,'uzas, idi pecaj nisi ti za papir i olovku','2020-10-04', 2);

INSERT INTO STAVKA (knjiga_id, recenzija_id, polica_id) VALUES (1, 1, 10);
INSERT INTO STAVKA (knjiga_id, polica_id) VALUES (2, 8);
INSERT INTO STAVKA (knjiga_id, polica_id) VALUES (1, 5);
INSERT INTO STAVKA (knjiga_id, recenzija_id, polica_id) VALUES (2, 1, 7);
INSERT INTO STAVKA (knjiga_id, recenzija_id, polica_id) VALUES (2, 1, 3);

INSERT INTO KNJIGA_ZANROVI (knjige_id, zanrovi_id) VALUES (1, 2);
INSERT INTO KNJIGA_ZANROVI (knjige_id, zanrovi_id) VALUES (2, 1);
INSERT INTO KNJIGA_ZANROVI (knjige_id, zanrovi_id) VALUES (2, 3);
INSERT INTO KNJIGA_ZANROVI (knjige_id, zanrovi_id) VALUES (2, 6);