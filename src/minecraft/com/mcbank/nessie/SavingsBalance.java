package com.mcbank.nessie;

public class SavingsBalance
{
    private String _id;
    private String type;
    private String nickname;
    private String rewards;
    private String balance;
    private String customer_id;

    public SavingsBalance()
    {
    }

    public String get_id()
    {
        return _id;
    }

    public void set_id(String _id)
    {
        this._id = _id;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    public String getRewards()
    {
        return rewards;
    }

    public void setRewards(String rewards)
    {
        this.rewards = rewards;
    }

    public String getBalance()
    {
        return balance;
    }

    public void setBalance(String balance)
    {
        this.balance = balance;
    }

    public String getCustomer_id()
    {
        return customer_id;
    }

    public void setCustomer_id(String customer_id)
    {
        this.customer_id = customer_id;
    }
}
