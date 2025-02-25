import repository.DokterRepository;
import repository.DokterRepositoryImpl;
import service.DokterService;
import service.DokterServiceImpl;
import util.DatabaseUtil;
import view.DokterView;

import javax.sql.DataSource;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        DataSource dataSource = DatabaseUtil.getDataSource();
        DokterRepository dokterRepository = new DokterRepositoryImpl(dataSource);
        DokterService dokterService = new DokterServiceImpl(dokterRepository);
        DokterView dokterView = new DokterView(dokterService, new Scanner(System.in));

        dokterView.menuDokter();
    }
}
