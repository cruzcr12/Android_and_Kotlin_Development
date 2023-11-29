package com.echdevelopment.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.echdevelopment.unitconverter.ui.theme.DarkPrimary
import com.echdevelopment.unitconverter.ui.theme.DarkSecondary
import com.echdevelopment.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitConverter(){

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters")}
    var outputUnit by remember { mutableStateOf("Meters") }
    var inputExpanded by remember { mutableStateOf(false) }
    var outputExpanded by remember { mutableStateOf(false) }
    // The multiplier we are going to apply to whatever the user has entered to give us
    // the output with the data type that fits for the second value
    val conversionFactor =  remember { mutableStateOf(1.0) }
    val outputConversionFactor = remember { mutableStateOf(1.0) }

    fun convertUnits(){
        // ?: = elvis operator
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100.0 / outputConversionFactor.value).roundToInt() / 100.0
        outputValue = result.toString()
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Here all the UI elements will be stacked below each other
        Text("Unit Converter", style = MaterialTheme.typography.headlineLarge, color = DarkSecondary)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                // Here goes what should happen when the value of OutlinedTextField changes
                inputValue = it
                convertUnits()
            },
            label = { Text("Enter value") })
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            // Input Box
            Box {
                // Input Button
                Button(onClick = { inputExpanded = true }) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = inputExpanded, onDismissRequest = { inputExpanded = false }) {
                    // Where the dropdownmenu items will be added
                    DropdownMenuItem(
                        text = { Text("Milimeters") },
                        onClick = {
                            inputExpanded = false
                            inputUnit = "Milimeters"
                            conversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            inputExpanded = false
                            inputUnit = "Centimeters"
                            conversionFactor.value = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            inputExpanded = false
                            inputUnit = "Meters"
                            conversionFactor.value = 1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            inputExpanded = false
                            inputUnit = "Feet"
                            // A feet is 0.3048 meters
                            conversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            // Output box
            Box {
                // Output button
                Button(onClick = { outputExpanded = true }) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = outputExpanded, onDismissRequest = { outputExpanded = false }) {
                    // Where the dropdownmenu items will be added
                    DropdownMenuItem(
                        text = { Text("Milimeters") },
                        onClick = {
                            outputExpanded = false
                            outputUnit = "Milimeters"
                            outputConversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            outputExpanded = false
                            outputUnit = "Centimeters"
                            outputConversionFactor.value = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            outputExpanded = false
                            outputUnit = "Meters"
                            outputConversionFactor.value = 1.0
                            convertUnits() }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            outputExpanded = false
                            outputUnit = "Feet"
                            // A feet is 0.3048 meters
                            outputConversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                }
            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        // Result Text
        Text("Result: $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium,
            color = DarkSecondary
            )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UnitConverterPreview() {
    UnitConverterTheme {
        UnitConverter()
    }
}