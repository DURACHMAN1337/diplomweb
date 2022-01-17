package com.company.diplomweb.web.screens;

import com.company.diplomweb.entity.AlgebraType;
import com.company.diplomweb.service.ExecuteReportService;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.gui.AppConfig;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.export.ExportDisplay;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;
import java.util.List;

@UiController("diplomweb_AlgebraLieScreen")
@UiDescriptor("algebra-lie-screen.xml")
public class AlgebraLieScreen extends Screen {
    @Inject
    private LookupField dimLookupField;
    @Inject
    private LookupField moduleLookupField;
    @Inject
    private LookupField<AlgebraType> algebraLookupField;
    @Inject
    private ExecuteReportService executeReportService;
    @Inject
    private ExportDisplay exportDisplay;

    @Subscribe("calculateButton")
    public void onCalculateButtonClick(Button.ClickEvent event) {
        exportDisplay.show(executeReport());
    }

    @Subscribe
    public void onAfterInit(AfterInitEvent event) {
        List<Integer> primeNumbers = List.of(2, 3, 5, 7, 11, 13);
        moduleLookupField.setOptionsList(primeNumbers);
    }



    @Subscribe("algebraLookupField")
    public void onAlgebraLookupFieldValueChange(HasValue.ValueChangeEvent<AlgebraType> event) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> evenNumbers = List.of(2, 4, 6, 8, 10);
        if (AlgebraType.SLNC.equals(event.getValue()))
            dimLookupField.setOptionsList(numbers);
        else if (AlgebraType.ONC.equals(event.getValue()) || AlgebraType.SPNC.equals(event.getValue()))
            dimLookupField.setOptionsList(evenNumbers);
    }

    public FileDescriptor executeReport(){
        String algebraName = algebraLookupField.getValue().getId();
        Number dim = (Number) dimLookupField.getValue();
        Number modP = (Number) moduleLookupField.getValue();
        return executeReportService.execute(algebraName,dim,modP);
    }
}