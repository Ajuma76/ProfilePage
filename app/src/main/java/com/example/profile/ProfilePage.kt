package com.example.profile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.layoutId
import kotlin.math.round


@Composable
fun ProfilePage() {
    //Content of our card - Including Dog Image, Description, followers etc

    Card(
        elevation = 6.dp, modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp, bottom = 100.dp, start = 16.dp, end = 16.dp)
            .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(30.dp))
    ) {
        BoxWithConstraints() {
            val constraints = if (minWidth < 600.dp){
                portraitConstraints(margin = 16.dp)
            }else{
                //TODO call landscapeConstraint
                landscapeConstraint(margin = 16.dp)
            }
        
        ConstraintLayout (constraints){
            Image(painter = painterResource(id = R.drawable.showcase),
                contentDescription = "Showcase",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .border(
                        width = 2.dp,
                        color = Color.Red,
                        shape = CircleShape
                    ).layoutId("image"))

            Text(text = "Programming School", fontWeight = FontWeight.Bold,
                modifier = Modifier.layoutId("titleText"))

            Text(text = "Let's Learn to Code, Shall We?",
                modifier = Modifier.layoutId("descText"))

            Row(horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .layoutId("stats")) {
                ProfileStats(stat = "123", title = "Following")
                ProfileStats(stat = "112", title = "Followers")
                ProfileStats(stat = "13", title = "Posts")
            }
            Button(onClick = { /*TODO*/ },
                modifier = Modifier.layoutId("followBtn")) {
                Text(text = "Follow")
            }

            Button(onClick = { /*TODO*/ },
                modifier = Modifier.layoutId("messageBtn")) {
                Text(text = "Message")
            }
        }
        }
    }
}

@Composable
fun ProfileStats(stat: String, title: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stat, fontWeight = FontWeight.Bold)
        Text(text = title)
    }
}

private fun portraitConstraints(margin:Dp): ConstraintSet{
    return ConstraintSet {
        //destructing data classes
        val image = createRefFor("image")
        val titleText = createRefFor("titleText")
        val descText = createRefFor("descText")
        val stats = createRefFor("stats")
        val followBtn = createRefFor("followBtn")
        val messageBtn = createRefFor("messageBtn")
        val guideline = createGuidelineFromTop(0.05f)
        constrain(image){
            top.linkTo(guideline)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(titleText){
            top.linkTo(image.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(descText){
            top.linkTo(titleText.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(stats){
            top.linkTo(descText.bottom)
        }
        constrain(followBtn){
            top.linkTo(stats.bottom, margin = margin)
            start.linkTo(parent.start)
            end.linkTo(messageBtn.start)
            width = Dimension.wrapContent
        }
        constrain(messageBtn){
            top.linkTo(stats.bottom, margin = margin)
            end.linkTo(parent.end)
            start.linkTo(followBtn.end)
            width = Dimension.wrapContent
        }
    }
}

private fun landscapeConstraint(margin:Dp): ConstraintSet{
    return ConstraintSet {
        val image = createRefFor("image")
        val titleText = createRefFor("titleText")
        val descText = createRefFor("descText")
        val stats = createRefFor("stats")
        val followBtn = createRefFor("followBtn")
        val messageBtn = createRefFor("messageBtn")

        constrain(image){
            top.linkTo(parent.top, margin = margin)
            start.linkTo(parent.start, margin = margin)
        }
        constrain(titleText){
            start.linkTo(image.start)
            top.linkTo(image.bottom)
        }

        constrain(descText){
            top.linkTo(titleText.bottom)
            start.linkTo(titleText.start)
            end.linkTo(titleText.end)
        }
        constrain(stats){
            top.linkTo(image.top)
            start.linkTo(image.end, margin = margin)
            end.linkTo(parent.end)
        }
        constrain(followBtn){
            top.linkTo(stats.bottom, margin = 16.dp)
            start.linkTo(stats.start)
            end.linkTo(messageBtn.start)
            bottom.linkTo(descText.bottom)
            width = Dimension.wrapContent
        }
        constrain(messageBtn){
            top.linkTo(stats.bottom, margin = 16.dp)
            start.linkTo(followBtn.end)
            end.linkTo(parent.end)
            bottom.linkTo(descText.bottom)
            width = Dimension.wrapContent
        }
    }
}

//image, titleText, descText, stats, followBtn, messageBtn

@Composable
@Preview(showBackground = true)
fun ProfilePagePreview(){
    ProfilePage()
}