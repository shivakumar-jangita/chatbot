package com.sap.fiori.copilot.agentmodel.context.query;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.sap.fiori.copilot.agentmodel.documentation.DocumentationConstants;

@ApiObject(name="QueryFilterRange",group=DocumentationConstants.DIGITAL_ASSISTANT_AGENT_FRAMEWORK,description="A standard representation of a filter")
public class QueryFilterRange {

	@ApiObjectField(name="Sign", description = "Sign used to either Include (\"I\") or exclude (\"E\") the results matching the range. Currently only \"I\" is supported")
	protected QueryFilterSign sign = QueryFilterSign.I;

	@ApiObjectField(name="operator", description = "Operator used for the range. Must match an OData ")
	protected QueryFilterOperator operator;
	
	@ApiObjectField(description = "Lowest value of the range. This is the one that will be used when igh is not set")
	protected String low;
	
	@ApiObjectField(description = "Highest value of the range. Will only be considered if the operatior is QueryOperator.BT, QueryOperator.LT or QueryOperator.GT")
	protected String high;
	
	@ApiObjectField(name="LowDisplayValue", description="Display value of a technical field. Ex: low is set to 00000001 that should be displayed as \"ACME\" in a customer filter scenario")
	protected String lowDisplayValue;
	
	public QueryFilterRange() {
		
	}

	public enum QueryFilterOperator {
		EQ("EQ"), BT("BT"), LT("LT"), LE("LE"), GT("GT"), GE("GE"), NE("NE"), UNKNOWN("UNKNOWN");

		private String queryOperator;

		private QueryFilterOperator(String value) {
			this.queryOperator = value;
		}

		@Override
		public String toString() {
			return this.queryOperator;
		}
	}

	public enum QueryFilterSign {
		I("I");
		private String querySign;

		QueryFilterSign(String querySign) {
			this.querySign = querySign;
		}

		@Override
		public String toString() {
			return this.querySign;
		}
	}

	public QueryFilterSign getSign() {
		// We only support I for now
		return QueryFilterSign.I;
	}

	public QueryFilterRange setSign(QueryFilterSign sign) {
		return this;
	}

	public QueryFilterOperator getOperator() {
		return operator;
	}

	public QueryFilterRange setOperator(QueryFilterOperator operator) {
		this.operator = operator;
		return this;
	}

	public String getLow() {
		return low;
	}

	public QueryFilterRange setLow(String low) {
		this.low = low;
		return this;
	}

	public String getHigh() {
		return high;
	}

	
	public QueryFilterRange setHigh(String high) {
		this.high = high;
		return this;
	}

	public String getLowDisplayValue() {
		return lowDisplayValue;
	}

	public void setLowDisplayValue(String lowDisplayValue) {
		this.lowDisplayValue = lowDisplayValue;
	}
	
	

}
