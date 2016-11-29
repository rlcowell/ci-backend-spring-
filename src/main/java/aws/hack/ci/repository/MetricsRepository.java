package aws.hack.ci.repository;


import aws.hack.ci.domain.DataPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by oleks on 11/28/2016.
 */
@Repository
@Transactional
public class MetricsRepository {

  @PersistenceContext
  private EntityManager entityManger;


  public List<DataPoint> getMetric() {
    Query query = entityManger.createNativeQuery(
      "SELECT s.country,\n" +
        "       s.landscape_no AS landscape,\n" +
        "       AVG(acidified_carbon) AS value\n" +
        "FROM public.eplotsoils_processed AS s\n" +
        "LEFT JOIN public.country AS c ON c.country = s.country\n" +
        "LEFT JOIN public.landscape AS l ON l.country = s.country AND l.landscape_no = s.landscape_no\n" +
        "WHERE soil_depth_class = 'top'\n" +
        "GROUP BY s.country, s.landscape_no\n",
      DataPoint.class);

    return query.getResultList();
  }
}