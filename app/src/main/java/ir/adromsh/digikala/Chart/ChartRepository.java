package ir.adromsh.digikala.Chart;

import java.util.List;

import ir.adromsh.digikala.Model.HistoryModel;
import io.reactivex.Single;

public class ChartRepository {
    ChartApiService chartApiService = new ChartApiService();

    public Single<List<HistoryModel>> getHistory(String id) {
        return chartApiService.getHistory(id);
    }
}
