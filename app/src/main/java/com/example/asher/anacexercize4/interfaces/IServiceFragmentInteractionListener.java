package com.example.asher.anacexercize4.interfaces;

public interface IServiceFragmentInteractionListener {
    void SetStatus(int serviceTypeId, String message);
    void SetLog(int logTypeId, String message);
}
