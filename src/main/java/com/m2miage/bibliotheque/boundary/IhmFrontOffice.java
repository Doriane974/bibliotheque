package com.m2miage.bibliotheque.boundary;

import com.m2miage.bibliotheque.control.GestionFrontOffice;
import com.m2miage.bibliotheque.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IhmFrontOffice {
    @Autowired
    private GestionFrontOffice gestionFrontOffice;

}
