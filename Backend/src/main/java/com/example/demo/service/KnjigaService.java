package com.example.demo.service;

import com.example.demo.dto.KnjigaDto;
import com.example.demo.entity.Autor;
import com.example.demo.entity.Knjiga;
import com.example.demo.entity.Polica;
import com.example.demo.entity.Zanr;
import com.example.demo.entity.Stavka;
import com.example.demo.repository.KnjigaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class KnjigaService {
    @Autowired
    private KnjigaRepository knjigaRepository;
    @Autowired
    private StavkaService stavkaService;
    @Autowired
    private ZanrService zanrService;

    public Knjiga findById(Long id) {
        return knjigaRepository.findById(id).orElse(null);
    }

    public Knjiga save(Knjiga knjiga) {
        return knjigaRepository.save(knjiga);
    }

    public void delete(Knjiga knjiga) {
        knjigaRepository.delete(knjiga);
    }

    public List<Knjiga> findAll() {
        return knjigaRepository.findAll();
    }

    public List<Knjiga> findAllByZanrovi(Zanr zanr) {
        return knjigaRepository.findAllByZanrovi(zanr);
    }

    public Knjiga findByISBN(String ISBN) {
        return knjigaRepository.findByISBN(ISBN).orElse(null);
    }

    public Knjiga dodajKnjigu(String naslov, String naslovnaFotografija, String isbn, Date datum, Integer str, String opis) {
        Knjiga knjiga = new Knjiga();

       /* try {
            naslovnaFotografija.transferTo(new File("src/main/resources/uploads/" + naslov + ".jpg"));
            knjiga.setNaslovnaFotografija("src/main/resources/uploads/" + naslov + ".jpg");
        } catch(Exception e) {
            knjiga.setNaslovnaFotografija(null);
        }*/

        knjiga.setISBN(isbn);
        knjiga.setNaslov(naslov);
        knjiga.setDatumObjavljivanja(datum);
        knjiga.setBrojStrana(str);
        knjiga.setOpis(opis);
        knjiga.setOcena(0.0);
        knjiga.setNaslovnaFotografija(naslovnaFotografija);
        knjiga = save(knjiga);
        return knjiga;
    }

    public Knjiga azurirajKnjigu(Knjiga knjiga, String naslov, MultipartFile naslovnaFotografija, String isbn, Date datum, Integer str, String opis) {

        if (str != null) {
            knjiga.setBrojStrana(str);
        }

        if (isbn != null) {
            knjiga.setISBN(isbn);
        }

        if (naslov != null) {
            knjiga.setNaslov(naslov);
        }

        if (opis != null) {
            knjiga.setOpis(opis);
        }

        if (datum != null) {
            knjiga.setDatumObjavljivanja(datum);
        }

        if (naslovnaFotografija != null) {
            try {
                knjiga.setNaslovnaFotografija("src/main/resources/uploads/" + naslov + ".jpg");
                naslovnaFotografija.transferTo(new File("src/main/resources/uploads/" + naslov + ".jpg"));
            } catch (Exception e) {}
        }

        return knjiga;
    }

    public Integer obrisiKnjigu(Knjiga knjiga) {
        // ako knjiga ima recenzije ne moze biti obrisana
        //a ako ima recenzije onda ima ocenu pa proveravam sa ocenom jer oceni mogu direktno da pristupim
        if(knjiga.getOcena()==null){
            for (Stavka s: stavkaService.findAll()) {
                if (s.getKnjiga().getId().equals(knjiga.getId())) {
                    stavkaService.obrisiStavku(s);
                }
            }

            delete(knjiga);

            return 0;
        }else if (knjiga.getOcena() > 0) {
            return 1;
        }

        for (Stavka s: stavkaService.findAll()) {
            if (s.getKnjiga().getId().equals(knjiga.getId())) {
                stavkaService.obrisiStavku(s);
            }
        }

        delete(knjiga);

        return 0;
    }

    public Boolean knjigaPripadaAutoru(Knjiga knjiga, Autor autor) {
        for (Knjiga k : autor.getKnjige()) {
            if (knjiga.getId().equals(k.getId())) {
                return true;
            }
        }
        return false;
    }

    public void azurirajOcenuKnjige(Long id) {
        Knjiga knjiga = findById(id);

        double ocena = 0;
        int brojRecenzija = 0;
        for (Stavka s : stavkaService.findAll()) {
            if (s.getKnjiga().equals(knjiga)) {
                if(s.getRecenzija()!=null) {
                    ocena += s.getRecenzija().getOcena();
                    brojRecenzija++;
                }
            }
        }
        ocena /= brojRecenzija;
        knjiga.setOcena(ocena);
        save(knjiga);
    }

    public Integer dodajZanr(Knjiga knjiga, Long idZanra) {
        Zanr zanr = zanrService.findById(idZanra);

        if (zanr == null) {
            return 1;
        }

        knjiga.getZanrovi().add(zanr);
        save(knjiga);

        return 0;
    }

    public Integer obrisiZanr(Knjiga knjiga, Long idZanra) {
        Zanr zanr = zanrService.findById(idZanra);

        if (zanr == null) {
            return 1;
        }

        knjiga.getZanrovi().remove(zanr);
        save(knjiga);

        return 0;
    }
}
