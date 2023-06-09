INSERT INTO KORISNIK (ime, prezime, korisnicko_ime, email, lozinka, uloga, datum_rodjenja, profilna_slika) VALUES ('J. K.', 'Rowling', 'rolovana', 'jkrowling@harrypotter.com', 'rolnica', 'AUTOR', '1965-07-31', '/slike/1.jpg');
INSERT INTO KORISNIK (ime, prezime, korisnicko_ime, email, lozinka, uloga) VALUES ('Dejan', 'Bogovac', 'Cutthroat', 'bogovac.in20.2021@uns.ac.rs', 'test123', 'ADMINISTRATOR');
INSERT INTO KORISNIK (ime, prezime, korisnicko_ime, email, lozinka, uloga) VALUES ('Anastasija', 'Terzic', 'Anastasija', 'terzic.in44.2021@uns.ac.rs', 'test123', 'ADMINISTRATOR');
INSERT INTO KORISNIK (ime, prezime, korisnicko_ime, email, lozinka, uloga) VALUES ('Milica', 'Mirkovic', 'MilicaM', 'mirkovic.pr44.2021@uns.ac.rs', 'test543', 'CITALAC');
INSERT INTO KORISNIK (ime, prezime, korisnicko_ime, email, lozinka, uloga) VALUES ('Marko', 'Markovic', 'MarkoMarko', 'markovic.in4.2020@uns.ac.rs', 'test321', 'CITALAC');
INSERT INTO KORISNIK (ime, prezime, korisnicko_ime, email, lozinka, uloga) VALUES ('Janko', 'Jankovic', 'JankoTheBoss', 'jankovic.ra122.2011@uns.ac.rs', 'test321', 'AUTOR');

INSERT INTO CITALAC (id) VALUES (1);
INSERT INTO CITALAC (id) VALUES (2);
INSERT INTO CITALAC (id) VALUES (3);
INSERT INTO CITALAC (id) VALUES (4);
INSERT INTO CITALAC (id) VALUES (5);
INSERT INTO CITALAC (id) VALUES (6);

INSERT INTO AUTOR (id, aktivan) VALUES (1, true);
INSERT INTO AUTOR (id, aktivan) VALUES (2, true);
INSERT INTO AUTOR (id, aktivan) VALUES (3, true);
INSERT INTO AUTOR (id, aktivan) VALUES (6, false);

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
INSERT INTO POLICA (naziv, primarna, korisnik_id) VALUES ('najbolje_2021', false, 4);
INSERT INTO POLICA (naziv, primarna, korisnik_id) VALUES ('Read', true, 4);
INSERT INTO POLICA (naziv, primarna, korisnik_id) VALUES ('ne_preporucujem', false, 5);
INSERT INTO POLICA (naziv, primarna, korisnik_id) VALUES ('Currently Reading', true, 5);
INSERT INTO POLICA (naziv, primarna, korisnik_id) VALUES ('Read', true, 6);
INSERT INTO POLICA (naziv, primarna, korisnik_id) VALUES ('najbolje drame', false, 6);

INSERT INTO ZAHTEV (email, telefon, poruka, datum, status, autor_id) VALUES ('deja.bogovac@gmail.com', '+111 111 111', 'pls gib author uwu >///<', '2023-04-10', 'ODOBREN', 3);
INSERT INTO ZAHTEV (email, telefon, poruka, datum, status, autor_id) VALUES ('deja.bogovac@gmail.com', '+222 131 131', 'autor?plz', '2022-01-11', 'CEKA', 6);

INSERT INTO KNJIGA (naslov,naslovna_fotografija,ISBN,datum_objavljivanja,broj_strana,opis,ocena) VALUES ('Sta nam svako telo govori','www.slika.com',12345,'2002-09-16',300,'bas dobra knjiga',10.0);
INSERT INTO KNJIGA (naslov,naslovna_fotografija,ISBN,datum_objavljivanja,broj_strana,opis,ocena) VALUES ('HARRY POTTER','www.hair_poter.kegenda',19945,'2000-01-11',500,'najjaca knjiga ikada!!!!',9.0);
INSERT INTO KNJIGA (naslov,naslovna_fotografija,ISBN,datum_objavljivanja,broj_strana,opis,ocena) VALUES ('Sanja','slika.jpg.com.rs.najjace',1155,'2002-01-11',150,'osnovna skola procitati',7.0);
INSERT INTO KNJIGA (naslov,naslovna_fotografija,ISBN,datum_objavljivanja,broj_strana,opis,ocena) VALUES ('atomske navike','www.nesto.rs',4454,'2019-06-08',200,null,null);

INSERT INTO ZANR(naziv)VALUES ('akcija');
INSERT INTO ZANR(naziv)VALUES ('edukativna');
INSERT INTO ZANR(naziv)VALUES ('drama');
INSERT INTO ZANR(naziv)VALUES ('komedija');
INSERT INTO ZANR(naziv)VALUES ('romantika');
INSERT INTO ZANR(naziv)VALUES ('horor');
INSERT INTO ZANR(naziv)VALUES ('triler');

INSERT INTO RECENZIJA(ocena,tekst,datum_recenzije, korisnik_id)VALUES (10.0,'sve bajno, sve sjajno. ala je lep ovaj svet ovde potok onde cvet ','2021-04-04', 2);
INSERT INTO RECENZIJA(ocena,tekst,datum_recenzije, korisnik_id)VALUES (1.0,'uzas, idi pecaj nisi ti za papir i olovku','2020-10-04', 2);
INSERT INTO RECENZIJA(ocena,tekst,datum_recenzije, korisnik_id)VALUES (9.0,'sjajno, mali fali za 10 ','2020-04-24', 4);
INSERT INTO RECENZIJA(ocena,tekst,datum_recenzije, korisnik_id)VALUES (5.0,'srednja zalost, ali moze da se izvuce pouka! ','2023-02-01', 5);

INSERT INTO STAVKA (knjiga_id, recenzija_id) VALUES (1, 1);
INSERT INTO STAVKA (knjiga_id) VALUES (2);
INSERT INTO STAVKA (knjiga_id) VALUES (1);
INSERT INTO STAVKA (knjiga_id) VALUES (3);
INSERT INTO STAVKA (knjiga_id, recenzija_id) VALUES (2, 1);
INSERT INTO STAVKA (knjiga_id, recenzija_id) VALUES (4, 3);

INSERT INTO POLICA_STAVKE (stavke_id, polica_id) VALUES (1, 10);
INSERT INTO POLICA_STAVKE (stavke_id, polica_id) VALUES (2, 8);
INSERT INTO POLICA_STAVKE (stavke_id, polica_id) VALUES (3, 5);
INSERT INTO POLICA_STAVKE (stavke_id, polica_id) VALUES (4, 6);
INSERT INTO POLICA_STAVKE (stavke_id, polica_id) VALUES (5, 7);
INSERT INTO POLICA_STAVKE (stavke_id, polica_id) VALUES (6, 11);


INSERT INTO KNJIGA_ZANROVI (knjiga_id, zanr_id) VALUES (1, 2);
INSERT INTO KNJIGA_ZANROVI (knjiga_id, zanr_id) VALUES (2, 1);
INSERT INTO KNJIGA_ZANROVI (knjiga_id, zanr_id) VALUES (2, 3);
INSERT INTO KNJIGA_ZANROVI (knjiga_id, zanr_id) VALUES (2, 6);
INSERT INTO KNJIGA_ZANROVI (knjiga_id, zanr_id) VALUES (4, 4);
INSERT INTO KNJIGA_ZANROVI (knjiga_id, zanr_id) VALUES (3, 7);