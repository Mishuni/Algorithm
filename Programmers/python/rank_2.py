from collections import defaultdict

def solution(n,results):
    # init
    cnt = 0
    wins, loses = defaultdict(set), defaultdict(set)

    # check first
    for battle in results:
        wins[battle[0]].add(battle[1])
        loses[battle[1]].add(battle[0])
    
    # check relations
    for i in range(1,n+1):
        for winner in loses[i] : wins[winner].update(wins[i])
        for loser in wins[i] : loses[loser].update(loses[i])

    #  count the number of nodes that can be ranked for sure
    for i in range(1,n+1):
        if(len(wins[i])+len(loses[i])==n-1):
            cnt += 1

    return cnt


results = [[4, 3], [4, 2], [3, 2], [1, 2], [2, 5]]
print(solution(5,results))