package com.hfad.to_dolist.presentation.components

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.time.LocalDate

@Composable
fun DatePickerDialog(
    onDateSelected: (LocalDate) -> Unit,// показывает выбранную дату
    onDismiss: () -> Unit //Для закрытия календаря
) {
    val context = LocalContext.current //получаем текущий контекст что б передать в функцию
    val calendar = Calendar.getInstance() //переменная для получения экземпляра класса Calendar в единственном варианте
    val year = calendar.get(Calendar.YEAR)//переменная для выбранного года
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePicker = DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = LocalDate.of(selectedYear, selectedMonth + 1, selectedDay)
            //LocalDate.of - лист для даты
            val today = LocalDate.now()//получаем текущую дату
            if (!selectedDate.isBefore(today)) { //проверяем что выбранная дата не позже текущей
                onDateSelected(selectedDate)
            } else {
                Toast.makeText(context, "Живите в настоящем!", Toast.LENGTH_LONG).show()
            }

        },
        year,
        month,
        day
    )
    datePicker.datePicker.minDate = calendar.timeInMillis //проверяем что выбранная дата не позже текущей
    datePicker.setOnDismissListener{ //закрытие календаря
        onDismiss()
    }
    datePicker.show() //показ календаря
}