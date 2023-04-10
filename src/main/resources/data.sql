# Popuniti tabele
INSERT INTO Autor (ime, prezime, korisnickoIme, email, lozinka, datumRodjenja, aktivan) VALUES ("J. K.", "Rowling", "rolovana", "jkrowling@harrypotter.com", "rolnica", "1965-07-31", true);

INSERT INTO Korisnik (ime, prezime, korisnickoIme, email, lozinka, admin) VALUES ("Dejan", "Bogovac", "Cutthroat", "bogovac.in20.2021@uns.ac.rs", "test123", true);
INSERT INTO Korisnik (ime, prezime, korisnickoIme, email, lozinka, admin) VALUES ("Anastasija", "Terzic", "Anastasija", "terzic.in44.2021@uns.ac.rs", "test123", true);

INSERT INTO Polica (naziv, primarna) VALUES ("Want To Read", true);
INSERT INTO Polica (naziv, primarna) VALUES ("Currently Reading", true);
INSERT INTO Polica (naziv, primarna) VALUES ("Read", true);
INSERT INTO Polica (naziv, primarna) VALUES ("Test", false);

INSERT INTO Zahtev (email, telefon, poruka, datum, status) VALUES ("bogovac.in20.2021@uns.ac.rs", "+111 111 111", "pls gib author uwu >///<", "2023-04-10", 0);