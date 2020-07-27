package ir.adromsh.digikala.Chart;

import java.util.List;

import ir.adromsh.digikala.Model.ApiProvider;
import ir.adromsh.digikala.Model.ApiService;
import ir.adromsh.digikala.Model.HistoryModel;
import io.reactivex.Single;

public class ChartApiService {
    public ApiService apiService=ApiProvider.apiProvider();

    public Single<List<HistoryModel>> getHistory(String id){
        return apiService.getHistory(id);
    }
}
