package com.example.finalassessmentandroid

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.finalassessmentandroid.Controllers.DishController
import com.example.finalassessmentandroid.Models.Dish
import com.example.finalassessmentandroid.Utility.ApiUtility
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.internal.filterList

@Composable
fun DishListScreen(navController: NavController) {

    val dishService = ApiUtility.apiUtilityFun().create(DishController::class.java)
    var dishList: List<Dish> = listOf()

    var filteredDishList: List<Dish> by remember {
        mutableStateOf(listOf())
    }

    var vegOnlyChecked by remember {
        mutableStateOf(false)
    }

    var allChecked by remember {
        mutableStateOf(false)
    }

//    if (vegOnlyChecked) {
//        filteredDishList = dishList.filter { it.tag == "Veg" }
//    }else{
//        filteredDishList = dishList
//    }

    LaunchedEffect(Unit) {
        val response = withContext(Dispatchers.IO){
            dishService.getAllDish()
        }
        if (response.isSuccessful) {
            response.body()?.let { data ->
                dishList = data.payload
                filteredDishList = data.payload
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Veg Only",
                modifier = Modifier
                    .padding(end = 10.dp)
            )
            Switch(checked = vegOnlyChecked, onCheckedChange = { vegOnly ->
                vegOnlyChecked = vegOnly
                filteredDishList = dishList.filter { it.tag == "Veg" }
            })
        }

        LazyColumn {

            items(filteredDishList) {dish ->
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .height(80.dp)
                        .clickable {
                            navController.navigate("dishdetailscreen/${dish.id}")
                        }
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(start = 15.dp, end = 15.dp)
                            .height(78.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {

                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .width(60.dp)
                                    .height(60.dp)
//                                    .clip(RoundedCornerShape(30.dp))
                                    .background(Color.White)

                            ) {

                                AsyncImage(
                                    model = dish.imageUrl,
                                    placeholder = painterResource(id = R.drawable.placeholder),
                                    error = painterResource(id = R.drawable.placeholder),
                                    contentDescription = "dish image",
                                    modifier = Modifier
                                        .width(50.dp)
                                        .height(50.dp)
                                )
                            }

                            Column(
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .padding(start = 10.dp)
                            ) {

                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(text = dish.name,
                                        fontFamily = FontFamily.Default,
                                        fontSize = 20.sp,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )

                                    if (dish.tag == "Veg") {
                                        Image(
                                            painter = painterResource(id = R.drawable.veg),
                                            contentDescription = "",
                                            modifier = Modifier
                                                .width(20.dp)
                                                .height(20.dp)
                                                .padding(start = 5.dp)
                                        )
                                    }else{
                                        Image(
                                            painter = painterResource(id = R.drawable.non_veg),
                                            contentDescription = "",
                                            modifier = Modifier
                                                .width(20.dp)
                                                .height(20.dp)
                                                .padding(start = 5.dp)
                                        )
                                    }
                                }

                                Text(text = "₹"+dish.price,
                                    fontFamily = FontFamily.Default,
                                    fontSize = 15.sp,
                                    maxLines = 1
                                )
                            }
                        }
                    }
                    Divider(
                        modifier = Modifier
                            .height(2.dp)
                            .background(Color.Black)
                    )
                }
            }
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun ProductListScreenPreview() {
//    DishListScreen()
//}