INSERT INTO POLICA (naziv, primarna) VALUES ('Want To Read', true);
INSERT INTO POLICA (naziv, primarna) VALUES ('Currently Reading', true);
INSERT INTO POLICA (naziv, primarna) VALUES ('Read', true);
INSERT INTO POLICA (naziv, primarna) VALUES ('Test', false);

INSERT INTO CITALAC (id, email, lozinka, currently_reading_id, want_to_read_id, read_id) VALUES (1, 'test@test.com', 'test123', 2, 1, 3);

INSERT INTO AUTOR (id, ime, prezime, korisnicko_ime, email, lozinka, datum_rodjenja, profilna_slika, aktivan, currently_reading_id, want_to_read_id, read_id) VALUES (1, 'J. K.', 'Rowling', 'rolovana', 'jkrowling@harrypotter.com', 'rolnica', '1965-07-31', '/slike/1.jpg', true, 2, 1, 3);

INSERT INTO ADMINISTRATOR (id, ime, prezime, korisnicko_ime, email, lozinka) VALUES (1, 'Dejan', 'Bogovac', 'Cutthroat', 'bogovac.in20.2021@uns.ac.rs', 'test123');
INSERT INTO ADMINISTRATOR (id, ime, prezime, korisnicko_ime, email, lozinka) VALUES (2, 'Anastasija', 'Terzic', 'Anastasija', 'terzic.in44.2021@uns.ac.rs', 'test123');

INSERT INTO ZAHTEV (email, telefon, poruka, datum, status) VALUES ('bogovac.in20.2021@uns.ac.rs', '+111 111 111', 'pls gib author uwu >///<', '2023-04-10', 0);

INSERT INTO KNJIGA (naslov,naslovna_fotografija,ISBN,datum_objavljivanja,broj_strana,opis,ocena) VALUES ('Sta nam svako telo govori','www.nesto.srbijaaaaa',12345,'2002-09-16',300,'bas dobra knjiga',10.0);
INSERT INTO KNJIGA (naslov,naslovna_fotografija,ISBN,datum_objavljivanja,broj_strana,opis,ocena) VALUES ('HARRY POTTER','www.hair_poter.kegenda',19945,'2000-01-11',500,'najjaca knjiga ikada!!!!',9.0);

INSERT INTO ZANR(naziv)VALUES ('komedija');
INSERT INTO ZANR(naziv)VALUES ('romantika');
INSERT INTO ZANR(naziv)VALUES ('horor');
INSERT INTO ZANR(naziv)VALUES ('triler');
INSERT INTO RECENZIJA(ocena,tekst,datum_recenzije)VALUES (10.0,'sve bajno, sve sjajno. ala je lep ovaj svet ovde potok onde cvet ','2021-04-04');
INSERT INTO RECENZIJA(ocena,tekst,datum_recenzije)VALUES (1.0,'uzas, idi pecaj nisi ti za papir i olovku','2020-10-04');

