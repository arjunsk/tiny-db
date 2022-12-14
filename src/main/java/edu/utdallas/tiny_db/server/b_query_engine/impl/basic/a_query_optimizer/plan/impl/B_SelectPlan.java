package edu.utdallas.tiny_db.server.b_query_engine.impl.basic.a_query_optimizer.plan.impl;

import edu.utdallas.tiny_db.server.a_frontend.common.domain.clause.A_Predicate;
import edu.utdallas.tiny_db.server.b_query_engine.impl.basic.a_query_optimizer.plan.Plan;
import edu.utdallas.tiny_db.server.b_query_engine.impl.basic.b_execution_engine.A_Select_RWRecordScan;
import edu.utdallas.tiny_db.server.d_storage_engine.RORecordScan;
import edu.utdallas.tiny_db.server.b_query_engine.common.catalog.table.TableDefinition;

/**
 * The Plan class corresponding to the <i>select</i>
 * relational algebra operator.
 *
 * @author Edward Sciore
 */
public class B_SelectPlan implements Plan {
    private Plan p;
    private A_Predicate pred;

    public B_SelectPlan(Plan p, A_Predicate pred) {
        this.p = p;
        this.pred = pred;
    }

    public RORecordScan open() {
        RORecordScan s = p.open();
        return new A_Select_RWRecordScan(s, pred);
    }


    public TableDefinition schema() {
        return p.schema();
    }

    @Override
    public int blocksAccessed() {
        return p.blocksAccessed();
    }
}
