package fei.stuba.socket.enums;

public enum Type {
        SNR("534e52"), // OUTPUT ID OF RACE
        RWM("52574d"), // OUTPUT WIND
        RDC("524443"), // START OF TRANSFER OF RANKING
        RCC("524343"), // RESULT OF RANKED COMPETITOR
        RTC("525443"), // RESULT OF TEMPORARY CLASSIFICATION
        RFC("524643"), // END OF TRANSFER OF RANKING
        RCF("524346"), //
        RFJ("52464a"), // END OF JUDGEMENT - RESULTS ARE VALID
        DFC("444643"), // OUTPUT DAY TIME
        TFC(""), // OUTPUT RACE TIME
        SCD("534344"), // SUPPLEMENTARY CLASSIFICATION DATA
        SCH("534348"), // SUPPLEMENTARY CLASSIFICATION HEADER
        RCV("524356"), //
        RCS("524353"); // DELETE RESULT
        public String label;
        private Type(String label){
            this.label=label;
        }
}
