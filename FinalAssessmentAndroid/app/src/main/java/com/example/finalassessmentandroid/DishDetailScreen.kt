package com.example.finalassessmentandroid

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.finalassessmentandroid.Controllers.DishController
import com.example.finalassessmentandroid.Models.Dish
import com.example.finalassessmentandroid.Utility.ApiUtility
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun DishDetailScreen(dishId: Long) {

    val dishService = ApiUtility.apiUtilityFun().create(DishController::class.java)

    var dish: Dish by remember {
        mutableStateOf(Dish(id = 0, name = "", price = 0.0, ingredients = "", imageUrl = "", prepTime = "", tag = ""))
    }

    LaunchedEffect(Unit) {
        val response = withContext(Dispatchers.IO){
            dishService.getDishById(dishId)
        }
        if (response.isSuccessful) {
            response.body()?.let { data ->
                dish = data.payload
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .height(700.dp)
    ) {

        AsyncImage(
            model = dish.imageUrl,
            placeholder = painterResource(id = R.drawable.placeholder),
            error = painterResource(id = R.drawable.placeholder),
            contentDescription = "product image",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(Color.LightGray)
        )

        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 5.dp)
        ) {
            if (dish.tag == "Veg") {
                Image(
                    painter = painterResource(id = R.drawable.veg),
                    contentDescription = "",
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp)
                        .padding(start = 5.dp)
                )
            }else{
                Image(
                    painter = painterResource(id = R.drawable.non_veg),
                    contentDescription = "",
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp)
                )
            }
        }

        Text(
            text = dish.name,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 5.dp)
        )

        Text(
            text = "₹" + dish.price.toString(),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 5.dp)
        )

        Text(
            text = dish.ingredients,
            fontSize = 15.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 5.dp)
        )

        Text(
            text = dish.prepTime,
            fontSize = 15.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 5.dp)
        )
        
        Button(onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(top = 100.dp)
        ) {
            Text(text = "Add To Cart")
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun DishDetailScreenPreview() {
//    DishDetailScreen(1)
//}