Chart.pluginService.register({
    beforeDraw: function (chart) {
        if (chart.config.options.elements.center) {
            //Get ctx from string
            var ctx = chart.chart.ctx;

            //Get options from the center object in options
            var centerConfig = chart.config.options.elements.center;
            var fontStyle = centerConfig.fontStyle || 'Arial';
            var txt = centerConfig.text;
            var color = centerConfig.color || '#000';
            var sidePadding = centerConfig.sidePadding || 20;
            var sidePaddingCalculated = (sidePadding/100) * (chart.innerRadius * 2);
            //Start with a base font of 30px
            ctx.font = "30px " + fontStyle;

            //Get the width of the string and also the width of the element minus 10 to give it 5px side padding
            var stringWidth = ctx.measureText(txt).width;
            var elementWidth = (chart.innerRadius * 2) - sidePaddingCalculated;

            // Find out how much the font can grow in width.
            var widthRatio = elementWidth / stringWidth;
            var newFontSize = Math.floor(30 * widthRatio);
            var elementHeight = (chart.innerRadius * 2);

            // Pick a new font size so it will not be larger than the height of label.
            var fontSizeToUse = Math.min(newFontSize, elementHeight);

            //Set font settings to draw it correctly.
            ctx.textAlign = 'center';
            ctx.textBaseline = 'middle';
            var centerX = ((chart.chartArea.left + chart.chartArea.right) / 2);
            var centerY = ((chart.chartArea.top + chart.chartArea.bottom) / 2);
            ctx.font = fontSizeToUse+"px " + fontStyle;
            ctx.fillStyle = color;

            //Draw text in center
            ctx.fillText(txt, centerX, centerY);
        }
    }
});

Chart.defaults.global.responsive = true;

window.onload = function () {
    var fat = document.getElementById('fat').value;
    var protein = document.getElementById('protein').value;
    var carbohydrates = document.getElementById('carbohydrates').value;
    var calories = document.getElementById('calories').value;

    if (fat <= 0 && protein <=0 && carbohydrates <= 0) fat = defaultValue = 1;


    var ctx = document.getElementById('myChart').getContext('2d');
    var doughnutChart = new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: ['Tłuszcz', 'Białko', 'Węglowodany'],
            datasets: [{
                data: [fat, protein, carbohydrates],
                backgroundColor: [
                    'rgba(255,0,0,0.5)',
                    'rgba(0,255,0,0.5)',
                    'rgba(0,0,255,0.5)',
                    'rgba(122,122,122,0.5)',
                ],
                borderAlign: 'inner',
            }]
        },

        options: {
            // animation: {
            //     animateScale: true
            // },

            elements: {
                center: {
                    text: calories + " kcal"
                }
            },
            rotation: 270,
            tooltips: {
                callbacks: {
                    label: function (tooltipItem, data) {
                        var dataset = data.datasets[tooltipItem.datasetIndex];
                        var total = dataset.data.reduce(function (previousValue, currentValue, currentIndex, array) {
                            return previousValue + currentValue;
                        });
                        var currentValue = dataset.data[tooltipItem.index];
                        var precentage = Math.floor(((currentValue / total) * 100) + 0.5);
                        return precentage + "%";
                    }
                }
            }
        }

    });
};

//     google.charts.load("current", {packages:["corechart"]});
//     google.charts.setOnLoadCallback(drawChart);
//     function drawChart() {
//         var data = google.visualization.arrayToDataTable([
//             ['Macro', 'Spożyte makroelementy'],
//             ['Tłuszcz', fat],
//             ['Białko',     protein],
//             ['Węglowodany',      carbohydrates]
//         ]);
//
//         var options = {
//             title: 'Stosunek makro do kalorii',
//             pieHole: 0.4,
//         };
//
//         var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
//         chart.draw(data, options);
//     }
//
// }

