package unlam.compiler.symboltable;

import java.util.Objects;

public class Symbol {
    private String name;
    private DataType type;
    private Integer intValue;
    private Double floatValue;
    private String stringValue;
    private Integer length;
    private Boolean isCte;

    public Symbol(String name, DataType type)
    {
        this.type = type;
        this.name = name;

        this.stringValue = null;
        this.length = null;
        this.intValue = null;
        this.floatValue = null;
        this.isCte = false;
    }

    public Symbol(String name, String value)
    {
        this.type = DataType.CTE_STRING;
        int final_length = 0;
        String final_name = "_EMPTY_STRING";
        String final_value = "";

        if(!value.equals("\"\""))
        {
            final_value = value;
            final_name = "_" + final_value;
            final_length = final_value.length();
        }

        this.name = final_name;
        this.stringValue = final_value;
        this.length = final_length;
        this.isCte = true;

        this.intValue = null;
        this.floatValue = null;
    }

    public Symbol(String name, Integer value)
    {
        this.type = DataType.CTE_INTEGER;
        this.name = name;
        this.intValue = value;
        this.floatValue = Double.valueOf(value);
        this.isCte = true;

        this.length = null;
        this.stringValue = null;
    }

    public Symbol(String name, Double value)
    {
        this.type = DataType.CTE_FLOAT;
        this.name = name;
        this.floatValue = value;
        this.isCte = true;

        this.length = null;
        this.stringValue = null;
        this.intValue = null;
    }

    private String getValueAsString()
    {
        if(this.type == DataType.CTE_STRING)
        {
            return this.stringValue;
        } else
        {
            if(this.type == DataType.CTE_FLOAT)
            {
                return this.floatValue.toString();
            } else
            {
                return this.intValue.toString();
            }
        }
    }

    public String getValueForAsm()
    {
        if(isCte())
        {
            if(this.type == DataType.CTE_STRING)
            {
                return "\"" + this.stringValue + "\"" + ",'$'";
            }
            return this.floatValue.toString();
        }
        return "?";
    }

    public String getAsmType()
    {
        if(this.type == DataType.CTE_STRING)
        {
            return "db";
        }
        return "dd";
    }

    public DataType getType()
    {
        return this.type;
    }

    private String getDataTypeAsString()
    {
        if(this.type == DataType.CTE_STRING)
        {
            return "CTE_STRING";
        } else
        {
            if(this.type == DataType.CTE_FLOAT)
            {
                return "CTE_FLOAT";
            } else
            {
                return "CTE_INTEGER";
            }
        }
    }

    public String getName()
    {
        return this.name;
    }

    public Integer getLength()
    {
        return Objects.requireNonNullElse(this.length, 0);
    }

    public String getStringValue()
    {
        return Objects.requireNonNullElse(this.stringValue, "");
    }

    public void setValue(String value)
    {
        this.stringValue = value;
        this.length = value.length();
    }

    public void setValue(Integer value)
    {
        this.intValue = value;
        this.floatValue = Double.valueOf(value);
    }

    public void setValue(Double value)
    {
        this.floatValue = value;
    }

    public Boolean isCte()
    {
        return this.isCte;
    }

    @Override
    public String toString()
    {
        String s = "";
        String sName = this.name == null ? "" : this.name;
        String sValue = "";
        if(!(this.intValue == null && this.floatValue == null && this.stringValue == null)) //Si alguno tiene valor
        {
            sValue = getValueAsString();
        }
        String sLength = this.length == null ? "" : this.length.toString();
        s = String.format("%-20s|%-20s|%-20s|%-20s", sName, getDataTypeAsString(), sValue, sLength);
        return s;
    }
}
