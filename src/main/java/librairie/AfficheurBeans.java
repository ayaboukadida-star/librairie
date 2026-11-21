package librairie;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import librairie.repository.LivreRepository;
import librairie.service.LivreService;

@Component
public class AfficheurBeans implements CommandLineRunner {

    private static final String PAQUET = "librairie";

    private final ApplicationContext ctx;

    public AfficheurBeans(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void run(String... args) {
        String[] controllers = ctx.getBeanNamesForAnnotation(RestController.class);
        String[] services = ctx.getBeanNamesForAnnotation(Service.class);
        String[] repositories = ctx.getBeanNamesForType(LivreRepository.class);

        System.out.println("=== Beans de la librairie ===");
        System.out.println("@RestController : " + deLaLibrairie(controllers));
        System.out.println("@Service : " + deLaLibrairie(services));
        System.out.println("LivreRepository : " + Arrays.toString(repositories));

        boolean singleton = ctx.isSingleton("livreService");
        System.out.println("livreService est un singleton : " + singleton);
        LivreService s1 = ctx.getBean(LivreService.class);
        LivreService s2 = ctx.getBean(LivreService.class);
        System.out.println("Meme instance a chaque appel : " + (s1 == s2));
    }

    private List<String> deLaLibrairie(String[] noms) {
        return Arrays.stream(noms)
                .filter(nom -> ctx.getType(nom).getName().startsWith(PAQUET))
                .sorted()
                .toList();
    }
}