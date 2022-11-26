package edu.utdallas.davisbase.query_engine.b0_planner.plan.impl;

import edu.utdallas.davisbase.query_engine.b0_planner.plan.Plan;
import edu.utdallas.davisbase.query_engine.d_scans.impl.ProjectScan;
import edu.utdallas.davisbase.query_engine.d_scans.Scan;
import edu.utdallas.davisbase.storage_engine.e_record.Schema;

import java.util.List;


public class ProjectPlan implements Plan {
    private Plan p;
    private Schema schema = new Schema();


    public ProjectPlan(Plan p, List<String> fieldlist) {
        this.p = p;
        for (String fldname : fieldlist)
            schema.add(fldname, p.schema());
    }


    public Scan open() {
        Scan s = p.open();
        return new ProjectScan(s, schema.fields());
    }

    public Schema schema() {
        return schema;
    }
}