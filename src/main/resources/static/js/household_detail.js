$(document).ready(function() {
    getChartData();
});

function getChartData(retry = false) {
    $.ajax({
        url: '/household/chart',
        method: 'get',
        success: function (data) {
            const code = data.code ?? null;

            if(code === "200") {
                const result = data.result ?? null;

                if(result) {
                    new Chart(document.getElementById('chart'), {
                        type: 'bar',
                        data: {
                            labels: result.map((row) => row.mon),
                            datasets: [{
                                data: result.map((row) => row.plusAmount),
                            }, {
                                data: result.map((row) => row.minusAmount),
                            }, {
                                data: result.map((row) => row.totalAmount),
                                type: 'line'
                            }]
                        },
                        options: {
                            scales: {
                                y: {
                                    beginAtZero: true
                                }
                            },
                            plugins: {
                                legend: {
                                    display: false
                                }
                            }
                        }
                    });
                }
            }
        }, error: function () {
            if(!retry) getChartData(true);
        }
    });
}