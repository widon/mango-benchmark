package cc.concurrent.mango.benchmark;

import cc.concurrent.mango.MethodStats;
import cc.concurrent.mango.benchmark.model.Stat;
import cc.concurrent.mango.benchmark.round.JdbcBatchInsertRound;
import cc.concurrent.mango.benchmark.round.MangoBatchInsertRound;
import cc.concurrent.mango.benchmark.util.Config;
import cc.concurrent.mango.benchmark.util.MangoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ash
 */
public class BatchInsertRunner {

    public static void main(String[] args) throws Exception {
        List<Stat> jdbcStats = new ArrayList<Stat>();
        List<Stat> mangoStats = new ArrayList<Stat>();
        int round = Config.getRound();
        for (int i = 0; i < round; i++) {
            Stat jdbcStat = new JdbcBatchInsertRound().run();
            Stat mangoStat = new MangoBatchInsertRound().run();
            jdbcStats.add(jdbcStat);
            mangoStats.add(mangoStat);
            System.out.println("round " + (i + 1) + " over!");
        }
        System.out.println("BatchInsertRunner");
        System.out.println("jdbcStats=" + jdbcStats);
        System.out.println("mangoStats=" + mangoStats);
        MethodStats stat = new ArrayList<MethodStats>(MangoUtil.getMango().getStatsMap().values()).get(0);
        System.out.println("mangoInnerAvg=" + stat.averageExecutePenalty());
    }
}
