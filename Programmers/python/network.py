def solution(n,computers):
    cnt = 0

    queue = [0]
    visit = [False for i in range(n)]
    visit[0] = True

    while(len(queue)!=0):
        node = queue.pop(0)
        for i,connect in enumerate(computers[node]):
            if(not visit[i] and connect ==1):
                queue.append(i)
                visit[i] = True
        
        if(len(queue)==0):
            cnt += 1
            for i in range(n):
                if(not visit[i]):
                    queue.append(i)
                    visit[i] = True
                    break
    return cnt



computers = [[1, 1, 0], [1, 1, 0], [0, 0, 1]]
n = 3
print(solution(n,computers))