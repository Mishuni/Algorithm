def solution(n, costs):
    answer = 0
    check = [costs[0][0]]

    while(len(check)!=n):
        min_dis=0
        min_idx = 0
        min_des = 0
        for i,cost in enumerate(costs):
            if(cost[0] in check and cost[1] not in check):
                if(min_dis==0 or cost[2]<min_dis):
                    min_dis = cost[2]
                    min_idx = i
                    min_des = cost[1]
            elif(cost[1] in check and cost[0] not in check):
                if(min_dis==0 or cost[2]<min_dis):
                    min_dis = cost[2]
                    min_idx = i
                    min_des = cost[0]
        answer = answer + min_dis
        del costs[min_idx]
        check.append(min_des)

    return answer


costs = [[0,2,2],[0,1,1],[1,2,5],[1,3,1],[2,3,8],[1,4,12],[2,4,3]]
print(solution(5,costs))

