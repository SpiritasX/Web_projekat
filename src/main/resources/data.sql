<<<<<<< HEAD
# Popuniti tabele
INSERT INTO Autor (ime, prezime, korisnickoIme, email, lozinka, datumRodjenja, aktivan) VALUES ("J. K.", "Rowling", "rolovana", "jkrowling@harrypotter.com", "rolnica", "1965-07-31", true);

INSERT INTO Korisnik (ime, prezime, korisnickoIme, email, lozinka, admin) VALUES ("Dejan", "Bogovac", "Cutthroat", "bogovac.in20.2021@uns.ac.rs", "test123", true);
INSERT INTO Korisnik (ime, prezime, korisnickoIme, email, lozinka, admin) VALUES ("Anastasija", "Terzic", "Anastasija", "terzic.in44.2021@uns.ac.rs", "test123", true);

INSERT INTO Polica (naziv, primarna) VALUES ("Want To Read", true);
INSERT INTO Polica (naziv, primarna) VALUES ("Currently Reading", true);
INSERT INTO Polica (naziv, primarna) VALUES ("Read", true);
INSERT INTO Polica (naziv, primarna) VALUES ("Test", false);

INSERT INTO Zahtev (email, telefon, poruka, datum, status) VALUES ("bogovac.in20.2021@uns.ac.rs", "+111 111 111", "pls gib author uwu >///<", "2023-04-10", 0);
=======
# Popuniti tabelu

INSERT INTO KNJIGA (naslov,naslovna_fotografije,ISBN,datum_objavljivanja,broj_strana,opis,zanr,ocena) VALUES ('Sta nam svako telo govori','www.nesto.srbijaaaaa',12345,'2002-09-16',300,'bas dobra knjiga','edukativni sadrzaj',10.0);
INSERT INTO KNJIGA (naslov,naslovna_fotografije,ISBN,datum_objavljivanja,broj_strana,opis,zanr,ocena) VALUES ('HARRY POTTER','www.hair_poter.kegenda',19945,'2000-01-11',500,'najjaca knjiga ikada!!!!','magija bebo',9.0);

INSERT INTO ZANR(naziv)VALUES ('komedija');
INSERT INTO ZANR(naziv)VALUES ('romantika');
INSERT INTO ZANR(naziv)VALUES ('horor');
INSERT INTO ZANR(naziv)VALUES ('triler');
INSERT INTO RECENZIJA(ocena,tekst,datum_recenzije,KORISNIK)VALUES (10.0,'sve bajno, sve sjajno. ala je lep ovaj svet ovde potok onde cvet ','2021-04-04','DEJAN');
INSERT INTO RECENZIJA(ocena,tekst,datum_recenzije,KORISNIK)VALUES (1.0,'uzas, idi pecaj nisi ti za papir i olovku','2020-10-04','ANASTASIJA');
>>>>>>> 4e36d9add78682339bff7457e5592ab0928ed147
