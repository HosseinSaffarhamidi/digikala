package ir.adromsh.digikala.Chart;

import java.util.List;

import ir.adromsh.digikala.Model.HistoryModel;
import io.reactivex.Single;

public class ChartViewModel {
    ChartRepository repository = new ChartRepository();

    public Single<List<HistoryModel>> getHistory(String id) {
        return repository.getHistory(id);
    }
}
